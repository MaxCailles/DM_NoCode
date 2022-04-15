import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import UtilisateurService from './utilisateur/utilisateur.service';
import CourseService from './course/course.service';
import RestaurantService from './restaurant/restaurant.service';
import ProduitService from './produit/produit.service';
import PanierService from './panier/panier.service';
import CooperativeService from './cooperative/cooperative.service';
import RoleService from './role/role.service';
import PayementService from './payement/payement.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('utilisateurService') private utilisateurService = () => new UtilisateurService();
  @Provide('courseService') private courseService = () => new CourseService();
  @Provide('restaurantService') private restaurantService = () => new RestaurantService();
  @Provide('produitService') private produitService = () => new ProduitService();
  @Provide('panierService') private panierService = () => new PanierService();
  @Provide('cooperativeService') private cooperativeService = () => new CooperativeService();
  @Provide('roleService') private roleService = () => new RoleService();
  @Provide('payementService') private payementService = () => new PayementService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
