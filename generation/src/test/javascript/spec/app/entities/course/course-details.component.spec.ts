/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CourseDetailComponent from '@/entities/course/course-details.vue';
import CourseClass from '@/entities/course/course-details.component';
import CourseService from '@/entities/course/course.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Course Management Detail Component', () => {
    let wrapper: Wrapper<CourseClass>;
    let comp: CourseClass;
    let courseServiceStub: SinonStubbedInstance<CourseService>;

    beforeEach(() => {
      courseServiceStub = sinon.createStubInstance<CourseService>(CourseService);

      wrapper = shallowMount<CourseClass>(CourseDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { courseService: () => courseServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCourse = { id: 123 };
        courseServiceStub.find.resolves(foundCourse);

        // WHEN
        comp.retrieveCourse(123);
        await comp.$nextTick();

        // THEN
        expect(comp.course).toBe(foundCourse);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCourse = { id: 123 };
        courseServiceStub.find.resolves(foundCourse);

        // WHEN
        comp.beforeRouteEnter({ params: { courseId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.course).toBe(foundCourse);
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
