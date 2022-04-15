import { IPanier } from '@/shared/model/panier.model';
import { IRestaurant } from '@/shared/model/restaurant.model';

export interface IProduit {
  id?: number;
  nom?: string;
  prix?: number;
  panier?: IPanier | null;
  restaurant?: IRestaurant | null;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public nom?: string,
    public prix?: number,
    public panier?: IPanier | null,
    public restaurant?: IRestaurant | null
  ) {}
}
