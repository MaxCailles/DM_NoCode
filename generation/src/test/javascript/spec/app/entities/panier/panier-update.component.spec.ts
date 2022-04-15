/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import PanierUpdateComponent from '@/entities/panier/panier-update.vue';
import PanierClass from '@/entities/panier/panier-update.component';
import PanierService from '@/entities/panier/panier.service';

import ProduitService from '@/entities/produit/produit.service';

import CourseService from '@/entities/course/course.service';

import PayementService from '@/entities/payement/payement.service';

import UtilisateurService from '@/entities/utilisateur/utilisateur.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Panier Management Update Component', () => {
    let wrapper: Wrapper<PanierClass>;
    let comp: PanierClass;
    let panierServiceStub: SinonStubbedInstance<PanierService>;

    beforeEach(() => {
      panierServiceStub = sinon.createStubInstance<PanierService>(PanierService);

      wrapper = shallowMount<PanierClass>(PanierUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          panierService: () => panierServiceStub,
          alertService: () => new AlertService(),

          produitService: () =>
            sinon.createStubInstance<ProduitService>(ProduitService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          courseService: () =>
            sinon.createStubInstance<CourseService>(CourseService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          payementService: () =>
            sinon.createStubInstance<PayementService>(PayementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          utilisateurService: () =>
            sinon.createStubInstance<UtilisateurService>(UtilisateurService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.panier = entity;
        panierServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(panierServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.panier = entity;
        panierServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(panierServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundPanier = { id: 123 };
        panierServiceStub.find.resolves(foundPanier);
        panierServiceStub.retrieve.resolves([foundPanier]);

        // WHEN
        comp.beforeRouteEnter({ params: { panierId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.panier).toBe(foundPanier);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
