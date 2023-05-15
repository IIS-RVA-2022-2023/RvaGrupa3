import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Dobavljac } from 'src/app/models/dobavljac';
import { DobavljacService } from 'src/app/services/dobavljac.service';

@Component({
  selector: 'app-dobavljac-dialog',
  templateUrl: './dobavljac-dialog.component.html',
  styleUrls: ['./dobavljac-dialog.component.css'],
})
export class DobavljacDialogComponent {
  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Dobavljac>,
    @Inject(MAT_DIALOG_DATA) public data: Dobavljac,
    public dobavljacService: DobavljacService
  ) {}

  public add(): void {
    this.dobavljacService.addDobavljac(this.data).subscribe(() => {
      this.snackBar.open(
        'Dobavljac sa nazivom: ' + this.data.naziv + ' je uspesno dodat!',
        'Ok',
        { duration: 4500 }
      );
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'Ok', { duration: 2500 });
      };
  }

  public update(): void {
    this.dobavljacService.updateDobavljac(this.data).subscribe(() => {
      this.snackBar.open(
        'Dobavljac sa ID: ' + this.data.id + ' je uspesno izmenjen!',
        'Ok',
        { duration: 4500 }
      );
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'Ok', { duration: 2500 });
      };
  }

  public delete(): void {
    this.dobavljacService.deleteDobavljac(this.data.id).subscribe(() => {
      this.snackBar.open('Dobavljac je izbrisan!', 'Ok', { duration: 4500 });
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'Ok', { duration: 2500 });
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmena', 'Ok', { duration: 2500 });
  }
}
