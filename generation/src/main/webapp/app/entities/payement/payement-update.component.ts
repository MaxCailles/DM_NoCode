import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import PanierService from '@/entities/panier/panier.service';
import { IPanier } from '@/shared/model/panier.model';

import { IPayement, Payement } from '@/shared/model/payement.model';
import PayementService from './payement.service';

const validations: any = {
  payement: {},
};

@Component({
  validations,
})
export default class PayementUpdate extends Vue {
  @Inject('payementService') private payementService: () => PayementService;
  @Inject('alertService') private alertService: () => AlertService;

  public payement: IPayement = new Payement();

  @Inject('panierService') private panierService: () => PanierService;

  public paniers: IPanier[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.payementId) {
        vm.retrievePayement(to.params.payementId);
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
    if (this.payement.id) {
      this.payementService()
        .update(this.payement)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.payement.updated', { param: param.id });
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
      this.payementService()
        .create(this.payement)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.payement.created', { param: param.id });
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

  public retrievePayement(payementId): void {
    this.payementService()
      .find(payementId)
      .then(res => {
        this.payement = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.panierService()
      .retrieve()
      .then(res => {
        this.paniers = res.data;
      });
  }
}
