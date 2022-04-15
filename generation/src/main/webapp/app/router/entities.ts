import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Utilisateur = () => import('@/entities/utilisateur/utilisateur.vue');
// prettier-ignore
const UtilisateurUpdate = () => import('@/entities/utilisateur/utilisateur-update.vue');
// prettier-ignore
const UtilisateurDetails = () => import('@/entities/utilisateur/utilisateur-details.vue');
// prettier-ignore
const Course = () => import('@/entities/course/course.vue');
// prettier-ignore
const CourseUpdate = () => import('@/entities/course/course-update.vue');
// prettier-ignore
const CourseDetails = () => import('@/entities/course/course-details.vue');
// prettier-ignore
const Restaurant = () => import('@/entities/restaurant/restaurant.vue');
// prettier-ignore
const RestaurantUpdate = () => import('@/entities/restaurant/restaurant-update.vue');
// prettier-ignore
const RestaurantDetails = () => import('@/entities/restaurant/restaurant-details.vue');
// prettier-ignore
const Produit = () => import('@/entities/produit/produit.vue');
// prettier-ignore
const ProduitUpdate = () => import('@/entities/produit/produit-update.vue');
// prettier-ignore
const ProduitDetails = () => import('@/entities/produit/produit-details.vue');
// prettier-ignore
const Panier = () => import('@/entities/panier/panier.vue');
// prettier-ignore
const PanierUpdate = () => import('@/entities/panier/panier-update.vue');
// prettier-ignore
const PanierDetails = () => import('@/entities/panier/panier-details.vue');
// prettier-ignore
const Cooperative = () => import('@/entities/cooperative/cooperative.vue');
// prettier-ignore
const CooperativeUpdate = () => import('@/entities/cooperative/cooperative-update.vue');
// prettier-ignore
const CooperativeDetails = () => import('@/entities/cooperative/cooperative-details.vue');
// prettier-ignore
const Role = () => import('@/entities/role/role.vue');
// prettier-ignore
const RoleUpdate = () => import('@/entities/role/role-update.vue');
// prettier-ignore
const RoleDetails = () => import('@/entities/role/role-details.vue');
// prettier-ignore
const Payement = () => import('@/entities/payement/payement.vue');
// prettier-ignore
const PayementUpdate = () => import('@/entities/payement/payement-update.vue');
// prettier-ignore
const PayementDetails = () => import('@/entities/payement/payement-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'utilisateur',
      name: 'Utilisateur',
      component: Utilisateur,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'utilisateur/new',
      name: 'UtilisateurCreate',
      component: UtilisateurUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'utilisateur/:utilisateurId/edit',
      name: 'UtilisateurEdit',
      component: UtilisateurUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'utilisateur/:utilisateurId/view',
      name: 'UtilisateurView',
      component: UtilisateurDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'course',
      name: 'Course',
      component: Course,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'course/new',
      name: 'CourseCreate',
      component: CourseUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'course/:courseId/edit',
      name: 'CourseEdit',
      component: CourseUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'course/:courseId/view',
      name: 'CourseView',
      component: CourseDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant',
      name: 'Restaurant',
      component: Restaurant,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant/new',
      name: 'RestaurantCreate',
      component: RestaurantUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant/:restaurantId/edit',
      name: 'RestaurantEdit',
      component: RestaurantUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant/:restaurantId/view',
      name: 'RestaurantView',
      component: RestaurantDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit',
      name: 'Produit',
      component: Produit,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/new',
      name: 'ProduitCreate',
      component: ProduitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/:produitId/edit',
      name: 'ProduitEdit',
      component: ProduitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/:produitId/view',
      name: 'ProduitView',
      component: ProduitDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'panier',
      name: 'Panier',
      component: Panier,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'panier/new',
      name: 'PanierCreate',
      component: PanierUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'panier/:panierId/edit',
      name: 'PanierEdit',
      component: PanierUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'panier/:panierId/view',
      name: 'PanierView',
      component: PanierDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative',
      name: 'Cooperative',
      component: Cooperative,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative/new',
      name: 'CooperativeCreate',
      component: CooperativeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative/:cooperativeId/edit',
      name: 'CooperativeEdit',
      component: CooperativeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative/:cooperativeId/view',
      name: 'CooperativeView',
      component: CooperativeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role',
      name: 'Role',
      component: Role,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role/new',
      name: 'RoleCreate',
      component: RoleUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role/:roleId/edit',
      name: 'RoleEdit',
      component: RoleUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role/:roleId/view',
      name: 'RoleView',
      component: RoleDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payement',
      name: 'Payement',
      component: Payement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payement/new',
      name: 'PayementCreate',
      component: PayementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payement/:payementId/edit',
      name: 'PayementEdit',
      component: PayementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payement/:payementId/view',
      name: 'PayementView',
      component: PayementDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
