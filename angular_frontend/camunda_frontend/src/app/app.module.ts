import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient} from '@angular/common/http'; // Import HttpClientModule
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, FormsModule], // Add HttpClientModule here
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule {}