package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Service;

public interface ServiceRepositry {

	List<Service> findAll();
	List<Service> findByServiceCode(String serviceCode);
}
