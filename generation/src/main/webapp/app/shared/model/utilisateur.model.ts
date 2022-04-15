import { ICourse } from '@/shared/model/course.model';
import { IPanier } from '@/shared/model/panier.model';
import { ICooperative } from '@/shared/model/cooperative.model';
import { IRole } from '@/shared/model/role.model';

export interface IUtilisateur {
  id?: number;
  pseudo?: string;
  email?: string;
  numeroTelephone?: string;
  courses?: ICourse[] | null;
  paniers?: IPanier[] | null;
  cooperatives?: ICooperative[] | null;
  roles?: IRole[] | null;
}

export class Utilisateur implements IUtilisateur {
  constructor(
    public id?: number,
    public pseudo?: string,
    public email?: string,
    public numeroTelephone?: string,
    public courses?: ICourse[] | null,
    public paniers?: IPanier[] | null,
    public cooperatives?: ICooperative[] | null,
    public roles?: IRole[] | null
  ) {}
}
