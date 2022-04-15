<template>
  <div>
    <b-dropdown-item to="/utilisateur">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.utilisateur')">Utilisateur</span>
    </b-dropdown-item>
    <b-dropdown-item to="/course">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.course')">Course</span>
    </b-dropdown-item>
    <b-dropdown-item to="/restaurant">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.restaurant')">Restaurant</span>
    </b-dropdown-item>
    <b-dropdown-item to="/produit">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.produit')">Produit</span>
    </b-dropdown-item>
    <b-dropdown-item to="/panier">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.panier')">Panier</span>
    </b-dropdown-item>
    <b-dropdown-item to="/cooperative">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.cooperative')">Cooperative</span>
    </b-dropdown-item>
    <b-dropdown-item to="/role">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.role')">Role</span>
    </b-dropdown-item>
    <b-dropdown-item to="/payement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.payement')">Payement</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
