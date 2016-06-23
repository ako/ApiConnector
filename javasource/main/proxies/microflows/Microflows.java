// This file was generated by Mendix Modeler 6.0.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package main.proxies.microflows;

import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.MendixRuntimeException;
import com.mendix.systemwideinterfaces.core.IContext;

public class Microflows
{
	// These are the microflows for the Main module
	public static String getScheduledEventInformationApi(IContext context, String _payload, String _mimetype, String _url, String _operation, String _eventname)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("eventname", _eventname);
			return (String)Core.execute(context, "Main.GetScheduledEventInformationApi", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static String getUserInfoApi(IContext context, String _payload, String _mimetype, String _url, String _operation, String _username)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("username", _username);
			return (String)Core.execute(context, "Main.GetUserInfoApi", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static void getUserSessionInfoApi(IContext context, String _payload, String _mimetype, String _url, String _operation, String _eventname)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("eventname", _eventname);
			Core.execute(context, "Main.GetUserSessionInfoApi", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static boolean initApp(IContext context)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			return (Boolean)Core.execute(context, "Main.InitApp", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static boolean registerApis(IContext context)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			return (Boolean)Core.execute(context, "Main.RegisterApis", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
}