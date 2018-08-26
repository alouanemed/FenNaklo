package com.malouane.fenkolo.features.list;

import com.malouane.fenkolo.domain.entity.Location;

public class VenueModel {
  private String id;
  private String name;
  private Location location;

  public VenueModel(String id, String name, Location location) {
    this.id = id;
    this.name = name;
    this.location = location;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
