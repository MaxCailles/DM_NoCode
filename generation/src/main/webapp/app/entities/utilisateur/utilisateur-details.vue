<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="utilisateur">
        <h2 class="jh-entity-heading" data-cy="utilisateurDetailsHeading">
          <span v-text="$t('coopcycleApp.utilisateur.detail.title')">Utilisateur</span> {{ utilisateur.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('coopcycleApp.utilisateur.pseudo')">Pseudo</span>
          </dt>
          <dd>
            <span>{{ utilisateur.pseudo }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.utilisateur.email')">Email</span>
          </dt>
          <dd>
            <span>{{ utilisateur.email }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.utilisateur.numeroTelephone')">Numero Telephone</span>
          </dt>
          <dd>
            <span>{{ utilisateur.numeroTelephone }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="utilisateur.id"
          :to="{ name: 'UtilisateurEdit', params: { utilisateurId: utilisateur.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./utilisateur-details.component.ts"></script>
