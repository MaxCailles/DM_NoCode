import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICourse } from '@/shared/model/course.model';
import CourseService from './course.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class CourseDetails extends Vue {
  @Inject('courseService') private courseService: () => CourseService;
  @Inject('alertService') private alertService: () => AlertService;

  public course: ICourse = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.courseId) {
        vm.retrieveCourse(to.params.courseId);
      }
    });
  }

  public retrieveCourse(courseId) {
    this.courseService()
      .find(courseId)
      .then(res => {
        this.course = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
