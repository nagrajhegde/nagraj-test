/**
 *
 */
package org.mycustomextension;

import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.model.BusinessProcessModel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.cockpitng.config.jaxb.wizard.CustomType;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandler;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandlerAdapter;


/**
 * @author I328847
 *
 */
public class VariantBuildingHandler implements FlowActionHandler
{

	private BusinessProcessService businessProcessService;

	@Override
	public void perform(final CustomType customType, final FlowActionHandlerAdapter adapter, final Map<String, String> parameters)
	{
		final Map<String, Object> currentContext = adapter.getWidgetInstanceManager().getModel().getValue("currentContext",
				Map.class);

		final BusinessProcessModel businessProcess = (BusinessProcessModel) currentContext.get("businessProcess");
		final String targetStep = (String) currentContext.get("targetStep");

		businessProcessService.restartProcess(businessProcess, targetStep);
		currentContext.put("successful", Boolean.TRUE);

		adapter.custom();
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}
}
