<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="course">
        <h2 class="jh-entity-heading" data-cy="courseDetailsHeading">
          <span v-text="$t('coopcycleApp.course.detail.title')">Course</span> {{ course.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('coopcycleApp.course.numero')">Numero</span>
          </dt>
          <dd>
            <span>{{ course.numero }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.course.date')">Date</span>
          </dt>
          <dd>
            <span v-if="course.date">{{ $d(Date.parse(course.date), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.course.panier')">Panier</span>
          </dt>
          <dd>
            <div v-if="course.panier">
              <router-link :to="{ name: 'PanierView', params: { panierId: course.panier.id } }">{{ course.panier.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.course.utilisateur')">Utilisateur</span>
          </dt>
          <dd>
            <div v-if="course.utilisateur">
              <router-link :to="{ name: 'UtilisateurView', params: { utilisateurId: course.utilisateur.id } }">{{
                course.utilisateur.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="course.id" :to="{ name: 'CourseEdit', params: { courseId: course.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./course-details.component.ts"></script>
