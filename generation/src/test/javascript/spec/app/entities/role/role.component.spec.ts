/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RoleComponent from '@/entities/role/role.vue';
import RoleClass from '@/entities/role/role.component';
import RoleService from '@/entities/role/role.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Role Management Component', () => {
    let wrapper: Wrapper<RoleClass>;
    let comp: RoleClass;
    let roleServiceStub: SinonStubbedInstance<RoleService>;

    beforeEach(() => {
      roleServiceStub = sinon.createStubInstance<RoleService>(RoleService);
      roleServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RoleClass>(RoleComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          roleService: () => roleServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      roleServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRoles();
      await comp.$nextTick();

      // THEN
      expect(roleServiceStub.retrieve.called).toBeTruthy();
      expect(comp.roles[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      roleServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(roleServiceStub.retrieve.callCount).toEqual(1);

      comp.removeRole();
      await comp.$nextTick();

      // THEN
      expect(roleServiceStub.delete.called).toBeTruthy();
      expect(roleServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
