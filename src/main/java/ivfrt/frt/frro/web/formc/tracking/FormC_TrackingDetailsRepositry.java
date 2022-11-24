package ivfrt.frt.frro.web.formc.tracking;

import java.util.List;

public interface FormC_TrackingDetailsRepositry {

	public List<FormC_TrackingDetails>  fetchTempApplicationDetails(String accoCode,String frroFroCode);
	public List<FormC_TrackingDetails>  fetchCheckOutPendingsApplicationDetails(String accoCode,String frroFroCode);
	public List<DepartDetailsPdf>  fetchDeparturedApplicationDetails(String accoCode,String frroFroCode);
	public List<DepartDetailsPdf> exportPdfOfDepartDetails(String applicationid);
	
}
