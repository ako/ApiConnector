package system;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

import com.mendix.core.actionmanagement.IActionRegistrator;

@Component(immediate = true)
public class UserActionsRegistrar
{
  @Reference
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.bundleComponentLoaded();
    registrator.registerUserAction(apiconnector.actions.ApiHeaderLocation.class);
    registrator.registerUserAction(apiconnector.actions.ApiResponseCacheControl.class);
    registrator.registerUserAction(apiconnector.actions.ApiResponseContentType.class);
    registrator.registerUserAction(apiconnector.actions.ApiStatus404NotFound.class);
    registrator.registerUserAction(apiconnector.actions.ApiStatusCreated201.class);
    registrator.registerUserAction(apiconnector.actions.RegisterApiEndpoint.class);
    registrator.registerUserAction(apiconnector.actions.RegisterApiEndpointOqlWithMappings.class);
    registrator.registerUserAction(apiconnector.actions.RegisterApiEndpointWithMappings.class);
    registrator.registerUserAction(apiconnector.actions.RegisterApiEndpointXpathWithMappings.class);
    registrator.registerUserAction(appcloudservices.actions.GenerateRandomPassword.class);
    registrator.registerUserAction(appcloudservices.actions.LogOutUser.class);
    registrator.registerUserAction(appcloudservices.actions.StartSignOnServlet.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
