import { IProduit } from '@/shared/model/produit.model';
import { ICourse } from '@/shared/model/course.model';
import { IPayement } from '@/shared/model/payement.model';
import { IUtilisateur } from '@/shared/model/utilisateur.model';

export interface IPanier {
  id?: number;
  prixtotal?: number;
  produits?: IProduit[] | null;
  course?: ICourse | null;
  payement?: IPayement | null;
  utilisateur?: IUtilisateur | null;
}

export class Panier implements IPanier {
  constructor(
    public id?: number,
    public prixtotal?: number,
    public produits?: IProduit[] | null,
    public course?: ICourse | null,
    public payement?: IPayement | null,
    public utilisateur?: IUtilisateur | null
  ) {}
}
