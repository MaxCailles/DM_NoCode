<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.utilisateur.home.createOrEditLabel"
          data-cy="UtilisateurCreateUpdateHeading"
          v-text="$t('coopcycleApp.utilisateur.home.createOrEditLabel')"
        >
          Create or edit a Utilisateur
        </h2>
        <div>
          <div class="form-group" v-if="utilisateur.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="utilisateur.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.utilisateur.pseudo')" for="utilisateur-pseudo">Pseudo</label>
            <input
              type="text"
              class="form-control"
              name="pseudo"
              id="utilisateur-pseudo"
              data-cy="pseudo"
              :class="{ valid: !$v.utilisateur.pseudo.$invalid, invalid: $v.utilisateur.pseudo.$invalid }"
              v-model="$v.utilisateur.pseudo.$model"
              required
            />
            <div v-if="$v.utilisateur.pseudo.$anyDirty && $v.utilisateur.pseudo.$invalid">
              <small class="form-text text-danger" v-if="!$v.utilisateur.pseudo.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.utilisateur.pseudo.minLength"
                v-text="$t('entity.validation.minlength', { min: 3 })"
              >
                This field is required to be at least 3 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.utilisateur.email')" for="utilisateur-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="utilisateur-email"
              data-cy="email"
              :class="{ valid: !$v.utilisateur.email.$invalid, invalid: $v.utilisateur.email.$invalid }"
              v-model="$v.utilisateur.email.$model"
              required
            />
            <div v-if="$v.utilisateur.email.$anyDirty && $v.utilisateur.email.$invalid">
              <small class="form-text text-danger" v-if="!$v.utilisateur.email.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.utilisateur.email.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Email' })"
              >
                This field should follow pattern for "Email".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.utilisateur.numeroTelephone')" for="utilisateur-numeroTelephone"
              >Numero Telephone</label
            >
            <input
              type="text"
              class="form-control"
              name="numeroTelephone"
              id="utilisateur-numeroTelephone"
              data-cy="numeroTelephone"
              :class="{ valid: !$v.utilisateur.numeroTelephone.$invalid, invalid: $v.utilisateur.numeroTelephone.$invalid }"
              v-model="$v.utilisateur.numeroTelephone.$model"
              required
            />
            <div v-if="$v.utilisateur.numeroTelephone.$anyDirty && $v.utilisateur.numeroTelephone.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.utilisateur.numeroTelephone.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
            </div>
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
            :disabled="$v.utilisateur.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./utilisateur-update.component.ts"></script>
