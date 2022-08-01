package ar.gob.inti.argentinaprograma.miportfolio.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(Class<?> instance, UUID id) {
    super(String.format("%s con id %d no encontrada", instance.getSimpleName(), id));
  }
}