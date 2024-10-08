package com.shms.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class IdDTO implements Serializable {
  protected String id;
}
