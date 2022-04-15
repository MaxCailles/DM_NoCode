import { IRestaurant } from '@/shared/model/restaurant.model';
import { IUtilisateur } from '@/shared/model/utilisateur.model';

export interface ICooperative {
  id?: number;
  name?: string;
  restaurants?: IRestaurant[] | null;
  utilisateur?: IUtilisateur | null;
}

export class Cooperative implements ICooperative {
  constructor(
    public id?: number,
    public name?: string,
    public restaurants?: IRestaurant[] | null,
    public utilisateur?: IUtilisateur | null
  ) {}
}
