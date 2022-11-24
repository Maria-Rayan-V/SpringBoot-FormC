package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Service;

public interface ServiceRepository {

	List<Service> findAll();
	List<Service> findAllForMobile();
	List<Service> findServiceByCode(String serviceCode);
}
