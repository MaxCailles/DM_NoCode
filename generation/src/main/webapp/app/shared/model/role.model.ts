import { IUtilisateur } from '@/shared/model/utilisateur.model';

export interface IRole {
  id?: number;
  role?: string;
  utilisateur?: IUtilisateur | null;
}

export class Role implements IRole {
  constructor(public id?: number, public role?: string, public utilisateur?: IUtilisateur | null) {}
}
