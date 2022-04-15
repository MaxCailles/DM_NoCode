/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CourseComponent from '@/entities/course/course.vue';
import CourseClass from '@/entities/course/course.component';
import CourseService from '@/entities/course/course.service';
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
  describe('Course Management Component', () => {
    let wrapper: Wrapper<CourseClass>;
    let comp: CourseClass;
    let courseServiceStub: SinonStubbedInstance<CourseService>;

    beforeEach(() => {
      courseServiceStub = sinon.createStubInstance<CourseService>(CourseService);
      courseServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CourseClass>(CourseComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          courseService: () => courseServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      courseServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCourses();
      await comp.$nextTick();

      // THEN
      expect(courseServiceStub.retrieve.called).toBeTruthy();
      expect(comp.courses[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      courseServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(courseServiceStub.retrieve.callCount).toEqual(1);

      comp.removeCourse();
      await comp.$nextTick();

      // THEN
      expect(courseServiceStub.delete.called).toBeTruthy();
      expect(courseServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
