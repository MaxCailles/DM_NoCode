import { IProduit } from '@/shared/model/produit.model';
import { ICooperative } from '@/shared/model/cooperative.model';

export interface IRestaurant {
  id?: number;
  nom?: string;
  produits?: IProduit[] | null;
  cooperative?: ICooperative | null;
}

export class Restaurant implements IRestaurant {
  constructor(public id?: number, public nom?: string, public produits?: IProduit[] | null, public cooperative?: ICooperative | null) {}
}
