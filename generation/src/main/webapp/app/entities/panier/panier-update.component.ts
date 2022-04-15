import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ProduitService from '@/entities/produit/produit.service';
import { IProduit } from '@/shared/model/produit.model';

import CourseService from '@/entities/course/course.service';
import { ICourse } from '@/shared/model/course.model';

import PayementService from '@/entities/payement/payement.service';
import { IPayement } from '@/shared/model/payement.model';

import UtilisateurService from '@/entities/utilisateur/utilisateur.service';
import { IUtilisateur } from '@/shared/model/utilisateur.model';

import { IPanier, Panier } from '@/shared/model/panier.model';
import PanierService from './panier.service';

const validations: any = {
  panier: {
    prixtotal: {
      required,
      numeric,
      min: minValue(0),
    },
  },
};

@Component({
  validations,
})
export default class PanierUpdate extends Vue {
  @Inject('panierService') private panierService: () => PanierService;
  @Inject('alertService') private alertService: () => AlertService;

  public panier: IPanier = new Panier();

  @Inject('produitService') private produitService: () => ProduitService;

  public produits: IProduit[] = [];

  @Inject('courseService') private courseService: () => CourseService;

  public courses: ICourse[] = [];

  @Inject('payementService') private payementService: () => PayementService;

  public payements: IPayement[] = [];

  @Inject('utilisateurService') private utilisateurService: () => UtilisateurService;

  public utilisateurs: IUtilisateur[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.panierId) {
        vm.retrievePanier(to.params.panierId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.panier.id) {
      this.panierService()
        .update(this.panier)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.panier.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.panierService()
        .create(this.panier)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.panier.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrievePanier(panierId): void {
    this.panierService()
      .find(panierId)
      .then(res => {
        this.panier = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.produitService()
      .retrieve()
      .then(res => {
        this.produits = res.data;
      });
    this.courseService()
      .retrieve()
      .then(res => {
        this.courses = res.data;
      });
    this.payementService()
      .retrieve()
      .then(res => {
        this.payements = res.data;
      });
    this.utilisateurService()
      .retrieve()
      .then(res => {
        this.utilisateurs = res.data;
      });
  }
}
