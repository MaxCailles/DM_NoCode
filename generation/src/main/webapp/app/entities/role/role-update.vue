<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.role.home.createOrEditLabel"
          data-cy="RoleCreateUpdateHeading"
          v-text="$t('coopcycleApp.role.home.createOrEditLabel')"
        >
          Create or edit a Role
        </h2>
        <div>
          <div class="form-group" v-if="role.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="role.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.role.role')" for="role-role">Role</label>
            <input
              type="text"
              class="form-control"
              name="role"
              id="role-role"
              data-cy="role"
              :class="{ valid: !$v.role.role.$invalid, invalid: $v.role.role.$invalid }"
              v-model="$v.role.role.$model"
              required
            />
            <div v-if="$v.role.role.$anyDirty && $v.role.role.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.role.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.role.role.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Role' })"
              >
                This field should follow pattern for "Role".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.role.utilisateur')" for="role-utilisateur">Utilisateur</label>
            <select class="form-control" id="role-utilisateur" data-cy="utilisateur" name="utilisateur" v-model="role.utilisateur">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="role.utilisateur && utilisateurOption.id === role.utilisateur.id ? role.utilisateur : utilisateurOption"
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
            :disabled="$v.role.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./role-update.component.ts"></script>
