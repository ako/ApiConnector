// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package apiconnector.actions;

import apiconnector.impl.ApiConnector;
import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

public class ApiStatusCreated201 extends CustomJavaAction<Boolean>
{
	public ApiStatusCreated201(IContext context)
	{
		super(context);
	}

	@Override
	public Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
        ILogNode logger = Core.getLogger(ApiHeaderLocation.class.getName());
        ApiConnector connector = new ApiConnector();
        connector.setApiStatusCode(getContext(),201);
        return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "ApiStatusCreated201";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
