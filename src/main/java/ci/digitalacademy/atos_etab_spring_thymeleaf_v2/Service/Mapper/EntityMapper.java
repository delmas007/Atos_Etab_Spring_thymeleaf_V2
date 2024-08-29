package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper;

public interface EntityMapper <D, E> {
    D fromEntity(E entity);
    E toEntity(D dto);

    interface AddressMapper {
    }
}
