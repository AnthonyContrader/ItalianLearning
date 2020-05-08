export class UserDTO {

   id: number;
   activated: boolean;
   email: string;
   firstName: string;
   lastName: string;
   login: string;
   createdBy: string;
   createdDate: string;
   imageUrl: string;
   langKey: string;
   lastModifiedBy: string;
   lastModifiedDate: string;

   authorities: string [];
}