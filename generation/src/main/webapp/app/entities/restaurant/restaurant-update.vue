<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.restaurant.home.createOrEditLabel"
          data-cy="RestaurantCreateUpdateHeading"
          v-text="$t('coopcycleApp.restaurant.home.createOrEditLabel')"
        >
          Create or edit a Restaurant
        </h2>
        <div>
          <div class="form-group" v-if="restaurant.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="restaurant.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.restaurant.nom')" for="restaurant-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="restaurant-nom"
              data-cy="nom"
              :class="{ valid: !$v.restaurant.nom.$invalid, invalid: $v.restaurant.nom.$invalid }"
              v-model="$v.restaurant.nom.$model"
              required
            />
            <div v-if="$v.restaurant.nom.$anyDirty && $v.restaurant.nom.$invalid">
              <small class="form-text text-danger" v-if="!$v.restaurant.nom.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.restaurant.nom.minLength"
                v-text="$t('entity.validation.minlength', { min: 2 })"
              >
                This field is required to be at least 2 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.restaurant.cooperative')" for="restaurant-cooperative"
              >Cooperative</label
            >
            <select
              class="form-control"
              id="restaurant-cooperative"
              data-cy="cooperative"
              name="cooperative"
              v-model="restaurant.cooperative"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  restaurant.cooperative && cooperativeOption.id === restaurant.cooperative.id ? restaurant.cooperative : cooperativeOption
                "
                v-for="cooperativeOption in cooperatives"
                :key="cooperativeOption.id"
              >
                {{ cooperativeOption.id }}
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
            :disabled="$v.restaurant.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./restaurant-update.component.ts"></script>
