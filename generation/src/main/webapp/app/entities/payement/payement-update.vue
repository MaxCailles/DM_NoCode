<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.payement.home.createOrEditLabel"
          data-cy="PayementCreateUpdateHeading"
          v-text="$t('coopcycleApp.payement.home.createOrEditLabel')"
        >
          Create or edit a Payement
        </h2>
        <div>
          <div class="form-group" v-if="payement.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="payement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.payement.panier')" for="payement-panier">Panier</label>
            <select class="form-control" id="payement-panier" data-cy="panier" name="panier" v-model="payement.panier">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="payement.panier && panierOption.id === payement.panier.id ? payement.panier : panierOption"
                v-for="panierOption in paniers"
                :key="panierOption.id"
              >
                {{ panierOption.id }}
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
            :disabled="$v.payement.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./payement-update.component.ts"></script>
