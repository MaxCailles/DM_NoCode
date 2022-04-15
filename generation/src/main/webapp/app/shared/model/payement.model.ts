import { IPanier } from '@/shared/model/panier.model';

export interface IPayement {
  id?: number;
  panier?: IPanier | null;
}

export class Payement implements IPayement {
  constructor(public id?: number, public panier?: IPanier | null) {}
}
