<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.produit.home.createOrEditLabel"
          data-cy="ProduitCreateUpdateHeading"
          v-text="$t('coopcycleApp.produit.home.createOrEditLabel')"
        >
          Create or edit a Produit
        </h2>
        <div>
          <div class="form-group" v-if="produit.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="produit.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.produit.nom')" for="produit-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="produit-nom"
              data-cy="nom"
              :class="{ valid: !$v.produit.nom.$invalid, invalid: $v.produit.nom.$invalid }"
              v-model="$v.produit.nom.$model"
              required
            />
            <div v-if="$v.produit.nom.$anyDirty && $v.produit.nom.$invalid">
              <small class="form-text text-danger" v-if="!$v.produit.nom.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.produit.nom.minLength" v-text="$t('entity.validation.minlength', { min: 2 })">
                This field is required to be at least 2 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.produit.nom.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Nom' })"
              >
                This field should follow pattern for "Nom".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.produit.prix')" for="produit-prix">Prix</label>
            <input
              type="number"
              class="form-control"
              name="prix"
              id="produit-prix"
              data-cy="prix"
              :class="{ valid: !$v.produit.prix.$invalid, invalid: $v.produit.prix.$invalid }"
              v-model.number="$v.produit.prix.$model"
              required
            />
            <div v-if="$v.produit.prix.$anyDirty && $v.produit.prix.$invalid">
              <small class="form-text text-danger" v-if="!$v.produit.prix.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.produit.prix.min" v-text="$t('entity.validation.min', { min: 0 })">
                This field should be at least 0.
              </small>
              <small class="form-text text-danger" v-if="!$v.produit.prix.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.produit.panier')" for="produit-panier">Panier</label>
            <select class="form-control" id="produit-panier" data-cy="panier" name="panier" v-model="produit.panier">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="produit.panier && panierOption.id === produit.panier.id ? produit.panier : panierOption"
                v-for="panierOption in paniers"
                :key="panierOption.id"
              >
                {{ panierOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.produit.restaurant')" for="produit-restaurant">Restaurant</label>
            <select class="form-control" id="produit-restaurant" data-cy="restaurant" name="restaurant" v-model="produit.restaurant">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="produit.restaurant && restaurantOption.id === produit.restaurant.id ? produit.restaurant : restaurantOption"
                v-for="restaurantOption in restaurants"
                :key="restaurantOption.id"
              >
                {{ restaurantOption.id }}
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
            :disabled="$v.produit.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./produit-update.component.ts"></script>
