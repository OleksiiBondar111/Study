package com.shms.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EntityDTO extends IdDTO {
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  protected Date lastModified;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  protected Date createdAt;
}
