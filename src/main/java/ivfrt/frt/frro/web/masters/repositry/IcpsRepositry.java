package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Icps;

public interface IcpsRepositry {

	List<Icps> findAll();
	List<Icps> findByStateCode(String stateCode);
	List<Icps> findIcpsByType(String icpTypeCode);
	List<Icps> findAllIcps();
	
}
