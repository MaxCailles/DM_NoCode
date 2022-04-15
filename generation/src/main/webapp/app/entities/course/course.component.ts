import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICourse } from '@/shared/model/course.model';

import CourseService from './course.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Course extends Vue {
  @Inject('courseService') private courseService: () => CourseService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public courses: ICourse[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCourses();
  }

  public clear(): void {
    this.retrieveAllCourses();
  }

  public retrieveAllCourses(): void {
    this.isFetching = true;
    this.courseService()
      .retrieve()
      .then(
        res => {
          this.courses = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ICourse): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCourse(): void {
    this.courseService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('coopcycleApp.course.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCourses();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
