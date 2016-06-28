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
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class Microflows
{
	// These are the microflows for the Main module
	public static String createProduct(IContext context, String _payload, String _mimetype, String _url, String _operation)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			return (String)Core.execute(context, "Main.CreateProduct", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.Products createUpdateProductV2(IContext context, main.proxies.Products _payload, String _mimetype, String _url, String _operation)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload == null ? null : _payload.getMendixObject());
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.CreateUpdateProductV2", params);
			return result == null ? null : main.proxies.Products.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static String getProductInfo(IContext context, String _payload, String _mimetype, String _url, String _operation, String _eAN)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("EAN", _eAN);
			return (String)Core.execute(context, "Main.GetProductInfo", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.Products getProductInfoV2(IContext context, String _payload, String _mimetype, String _url, String _operation, String _eAN)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("EAN", _eAN);
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.GetProductInfoV2", params);
			return result == null ? null : main.proxies.Products.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.util.List<main.proxies.Products> getProductsV2(IContext context, String _payload, String _mimetype, String _url, String _operation)
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			java.util.List<IMendixObject> objs = Core.execute(context, "Main.GetProductsV2", params);
			java.util.List<main.proxies.Products> result = null;
			if (objs != null)
			{
				result = new java.util.ArrayList<main.proxies.Products>();
				for (IMendixObject obj : objs)
					result.add(main.proxies.Products.initialize(context, obj));
			}
			return result;
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