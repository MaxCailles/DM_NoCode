<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.course.home.createOrEditLabel"
          data-cy="CourseCreateUpdateHeading"
          v-text="$t('coopcycleApp.course.home.createOrEditLabel')"
        >
          Create or edit a Course
        </h2>
        <div>
          <div class="form-group" v-if="course.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="course.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.course.numero')" for="course-numero">Numero</label>
            <input
              type="number"
              class="form-control"
              name="numero"
              id="course-numero"
              data-cy="numero"
              :class="{ valid: !$v.course.numero.$invalid, invalid: $v.course.numero.$invalid }"
              v-model.number="$v.course.numero.$model"
              required
            />
            <div v-if="$v.course.numero.$anyDirty && $v.course.numero.$invalid">
              <small class="form-text text-danger" v-if="!$v.course.numero.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.course.numero.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.course.date')" for="course-date">Date</label>
            <div class="d-flex">
              <input
                id="course-date"
                data-cy="date"
                type="datetime-local"
                class="form-control"
                name="date"
                :class="{ valid: !$v.course.date.$invalid, invalid: $v.course.date.$invalid }"
                required
                :value="convertDateTimeFromServer($v.course.date.$model)"
                @change="updateInstantField('date', $event)"
              />
            </div>
            <div v-if="$v.course.date.$anyDirty && $v.course.date.$invalid">
              <small class="form-text text-danger" v-if="!$v.course.date.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.course.date.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.course.panier')" for="course-panier">Panier</label>
            <select class="form-control" id="course-panier" data-cy="panier" name="panier" v-model="course.panier">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="course.panier && panierOption.id === course.panier.id ? course.panier : panierOption"
                v-for="panierOption in paniers"
                :key="panierOption.id"
              >
                {{ panierOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.course.utilisateur')" for="course-utilisateur">Utilisateur</label>
            <select class="form-control" id="course-utilisateur" data-cy="utilisateur" name="utilisateur" v-model="course.utilisateur">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="course.utilisateur && utilisateurOption.id === course.utilisateur.id ? course.utilisateur : utilisateurOption"
                v-for="utilisateurOption in utilisateurs"
                :key="utilisateurOption.id"
              >
                {{ utilisateurOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.course.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./course-update.component.ts"></script>
