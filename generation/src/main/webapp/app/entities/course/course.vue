<template>
  <div>
    <h2 id="page-heading" data-cy="CourseHeading">
      <span v-text="$t('coopcycleApp.course.home.title')" id="course-heading">Courses</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('coopcycleApp.course.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'CourseCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-course"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('coopcycleApp.course.home.createLabel')"> Create a new Course </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && courses && courses.length === 0">
      <span v-text="$t('coopcycleApp.course.home.notFound')">No courses found</span>
    </div>
    <div class="table-responsive" v-if="courses && courses.length > 0">
      <table class="table table-striped" aria-describedby="courses">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('coopcycleApp.course.numero')">Numero</span></th>
            <th scope="row"><span v-text="$t('coopcycleApp.course.date')">Date</span></th>
            <th scope="row"><span v-text="$t('coopcycleApp.course.panier')">Panier</span></th>
            <th scope="row"><span v-text="$t('coopcycleApp.course.utilisateur')">Utilisateur</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="course in courses" :key="course.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CourseView', params: { courseId: course.id } }">{{ course.id }}</router-link>
            </td>
            <td>{{ course.numero }}</td>
            <td>{{ course.date ? $d(Date.parse(course.date), 'short') : '' }}</td>
            <td>
              <div v-if="course.panier">
                <router-link :to="{ name: 'PanierView', params: { panierId: course.panier.id } }">{{ course.panier.id }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="course.utilisateur">
                <router-link :to="{ name: 'UtilisateurView', params: { utilisateurId: course.utilisateur.id } }">{{
                  course.utilisateur.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CourseView', params: { courseId: course.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CourseEdit', params: { courseId: course.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(course)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="coopcycleApp.course.delete.question" data-cy="courseDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-course-heading" v-text="$t('coopcycleApp.course.delete.question', { id: removeId })">
          Are you sure you want to delete this Course?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-course"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeCourse()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./course.component.ts"></script>
