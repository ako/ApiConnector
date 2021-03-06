// This file was generated by Mendix Modeler 7.1.
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
	public static java.lang.String addressSummary(IContext context, main.proxies.Address _address)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Address", _address == null ? null : _address.getMendixObject());
			return (java.lang.String)Core.execute(context, "Main.AddressSummary", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.lang.String convert_autonumber_to_string(IContext context, java.lang.Long _orderNumber)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("OrderNumber", _orderNumber);
			return (java.lang.String)Core.execute(context, "Main.convert_autonumber_to_string", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.lang.String convert_datetime_to_string(IContext context, java.util.Date _orderDate)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("OrderDate", _orderDate);
			return (java.lang.String)Core.execute(context, "Main.convert_datetime_to_string", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.lang.String convert_decimal_to_string(IContext context, java.math.BigDecimal _decimalToConvert)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("decimalToConvert", _decimalToConvert);
			return (java.lang.String)Core.execute(context, "Main.convert_decimal_to_string", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.lang.String createProduct(IContext context, java.lang.String _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			return (java.lang.String)Core.execute(context, "Main.CreateProduct", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.Product createUpdateProductV2(IContext context, main.proxies.Product _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload == null ? null : _payload.getMendixObject());
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.CreateUpdateProductV2", params);
			return result == null ? null : main.proxies.Product.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.Order getOrderFromOrder(IContext context, main.proxies.Order _order)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Order", _order == null ? null : _order.getMendixObject());
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.getOrderFromOrder", params);
			return result == null ? null : main.proxies.Order.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.Order getOrderV2(IContext context, java.lang.String _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation, java.lang.Long _orderNumber)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("OrderNumber", _orderNumber);
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.GetOrderV2", params);
			return result == null ? null : main.proxies.Order.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.ProductImage getProductImage(IContext context, java.lang.String _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation, java.lang.String _eAN, java.lang.Long _imageId)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("EAN", _eAN);
			params.put("ImageId", _imageId);
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.GetProductImage", params);
			return result == null ? null : main.proxies.ProductImage.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.lang.String getProductInfo(IContext context, java.lang.String _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation, java.lang.String _eAN)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("EAN", _eAN);
			return (java.lang.String)Core.execute(context, "Main.GetProductInfo", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.Product getProductInfoV2(IContext context, java.lang.String _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation, java.lang.String _eAN)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("EAN", _eAN);
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.GetProductInfoV2", params);
			return result == null ? null : main.proxies.Product.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static main.proxies.ProductReport getProductReport(IContext context, java.lang.String _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation, java.lang.String _eAN)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			params.put("EAN", _eAN);
			IMendixObject result = (IMendixObject)Core.execute(context, "Main.GetProductReport", params);
			return result == null ? null : main.proxies.ProductReport.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.util.List<main.proxies.Product> getProductsV2(IContext context, java.lang.String _payload, java.lang.String _mimetype, java.lang.String _url, java.lang.String _operation)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Payload", _payload);
			params.put("Mimetype", _mimetype);
			params.put("Url", _url);
			params.put("Operation", _operation);
			java.util.List<IMendixObject> objs = Core.execute(context, "Main.GetProductsV2", params);
			java.util.List<main.proxies.Product> result = null;
			if (objs != null)
			{
				result = new java.util.ArrayList<main.proxies.Product>();
				for (IMendixObject obj : objs)
					result.add(main.proxies.Product.initialize(context, obj));
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
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			return (java.lang.Boolean)Core.execute(context, "Main.InitApp", params);
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
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			return (java.lang.Boolean)Core.execute(context, "Main.RegisterApis", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
}