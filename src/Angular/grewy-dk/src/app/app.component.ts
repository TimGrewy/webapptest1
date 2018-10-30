import { Component } from '@angular/core';
import { TitleService } from '../title.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  titles = [];
  newTitle = "";

  constructor(private titleService: TitleService) { }

  ngOnInit() {
    this.getTitles();
  }

  getTitles(): void {
    this.titleService.getTitles()
        .subscribe(titles => this.titles = titles);
  }

    add(title: string): void {
      title = title.trim();
      if (!title) { return; }
      this.titleService.addTitle(title)
        .subscribe(title => {
          this.titles.push(title);
        });
    }

    delete(title: string): void {
      title = title.trim();
      if (!title) { return; }
      this.titleService.deleteTitle(name).subscribe();
      this.titles = this.titles.filter(h => h !== title);
    }
}
