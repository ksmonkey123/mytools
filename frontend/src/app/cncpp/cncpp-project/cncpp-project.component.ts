import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-cncpp-project',
  templateUrl: './cncpp-project.component.html',
  styleUrls: ['./cncpp-project.component.css']
})
export class CncppProjectComponent implements OnInit {

  constructor(private route: ActivatedRoute) {
  }

  projectId?: number;

  ngOnInit(): void {
    this.route.params.subscribe(params => {
        this.projectId = params['id'];
      }
    )
  }

}
