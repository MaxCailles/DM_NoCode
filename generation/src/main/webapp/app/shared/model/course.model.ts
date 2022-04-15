import { IPanier } from '@/shared/model/panier.model';
import { IUtilisateur } from '@/shared/model/utilisateur.model';

export interface ICourse {
  id?: number;
  numero?: number;
  date?: Date;
  panier?: IPanier | null;
  utilisateur?: IUtilisateur | null;
}

export class Course implements ICourse {
  constructor(
    public id?: number,
    public numero?: number,
    public date?: Date,
    public panier?: IPanier | null,
    public utilisateur?: IUtilisateur | null
  ) {}
}
