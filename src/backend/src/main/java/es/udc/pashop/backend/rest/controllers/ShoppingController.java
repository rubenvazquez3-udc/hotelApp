package es.udc.pashop.backend.rest.controllers;

import static es.udc.pashop.backend.rest.dtos.OrderConversor.toOrderDto;
import static es.udc.pashop.backend.rest.dtos.OrderConversor.toOrderSummaryDtos;
import static es.udc.pashop.backend.rest.dtos.ShoppingCartConversor.toShoppingCartDto;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.pashop.backend.model.exceptions.InstanceNotFoundException;
import es.udc.pashop.backend.model.exceptions.MaxItemsExceededException;
import es.udc.pashop.backend.model.exceptions.MaxQuantityExceededException;
import es.udc.pashop.backend.model.entities.Order;
import es.udc.pashop.backend.model.services.Block;
import es.udc.pashop.backend.model.exceptions.EmptyShoppingCartException;
import es.udc.pashop.backend.model.exceptions.PermissionException;
import es.udc.pashop.backend.model.services.ShoppingService;
import es.udc.pashop.backend.rest.common.ErrorsDto;
import es.udc.pashop.backend.rest.dtos.AddToShoppingCartParamsDto;
import es.udc.pashop.backend.rest.dtos.BlockDto;
import es.udc.pashop.backend.rest.dtos.BuyParamsDto;
import es.udc.pashop.backend.rest.dtos.IdDto;
import es.udc.pashop.backend.rest.dtos.OrderDto;
import es.udc.pashop.backend.rest.dtos.OrderSummaryDto;
import es.udc.pashop.backend.rest.dtos.RemoveShoppingCartItemParamsDto;
import es.udc.pashop.backend.rest.dtos.ShoppingCartDto;
import es.udc.pashop.backend.rest.dtos.UpdateShoppingCartItemQuantityParamsDto;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {
	
	private final static String MAX_QUANTITY_EXCEEDED_EXCEPTION_CODE = "project.exceptions.MaxQuantityExceededException";
	private final static String MAX_ITEMS_EXCEEDED_EXCEPTION_CODE = "project.exceptions.MaxItemsExceededException";
	private final static String EMPTY_SHOPPING_CART_EXCEPTION_CODE = "project.exceptions.EmptyShoppingCartException";
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@ExceptionHandler(MaxQuantityExceededException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleMaxQuantityExceededException(MaxQuantityExceededException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(MAX_QUANTITY_EXCEEDED_EXCEPTION_CODE,
			new Object[] {exception.getMaxAllowedIncrement()}, MAX_QUANTITY_EXCEEDED_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(MaxItemsExceededException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleMaxItemsExceededException(MaxItemsExceededException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(MAX_ITEMS_EXCEEDED_EXCEPTION_CODE, null,
			MAX_ITEMS_EXCEEDED_EXCEPTION_CODE, locale);
 
		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(EmptyShoppingCartException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleEmptyShoppingCartException(EmptyShoppingCartException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(EMPTY_SHOPPING_CART_EXCEPTION_CODE, null,
			EMPTY_SHOPPING_CART_EXCEPTION_CODE, locale);
 
		return new ErrorsDto(errorMessage);
		
	}
	
	@PostMapping("/shoppingcarts/{shoppingCartId}/addToShoppingCart")
	public ShoppingCartDto addToShoppingCart(@RequestAttribute Long userId, @PathVariable Long shoppingCartId, 
		@Validated @RequestBody AddToShoppingCartParamsDto params) 
		throws InstanceNotFoundException, PermissionException, MaxQuantityExceededException, MaxItemsExceededException {
		
		return toShoppingCartDto(shoppingService.addToShoppingCart(userId, shoppingCartId, params.getProductId(),
			params.getQuantity()));
		
	}
	
	@PostMapping("/shoppingcarts/{shoppingCartId}/updateShoppingCartItemQuantity")
	public ShoppingCartDto updateShoppingCartItemQuantity(@RequestAttribute Long userId, 
		@PathVariable Long shoppingCartId, @RequestBody UpdateShoppingCartItemQuantityParamsDto params)
		throws InstanceNotFoundException, PermissionException, MaxQuantityExceededException {
		
		return toShoppingCartDto(shoppingService.updateShoppingCartItemQuantity(userId, shoppingCartId,
			params.getProductId(), params.getQuantity()));
		
	}
	
	@PostMapping("/shoppingcarts/{shoppingCartId}/removeShoppingCartItem")
	public ShoppingCartDto removeShoppingCartItem(@RequestAttribute Long userId, @PathVariable Long shoppingCartId,
		@RequestBody RemoveShoppingCartItemParamsDto params) throws InstanceNotFoundException, PermissionException {
		
		return toShoppingCartDto(shoppingService.removeShoppingCartItem(userId, shoppingCartId, params.getProductId()));
		
	}
	
	@PostMapping("/shoppingcarts/{shoppingCartId}/buy")
	public IdDto buy(@RequestAttribute Long userId, @PathVariable Long shoppingCartId,
		@Validated @RequestBody BuyParamsDto params) 
		throws InstanceNotFoundException, PermissionException, EmptyShoppingCartException {
		
		return new IdDto(shoppingService.buy(userId, shoppingCartId, params.getPostalAddress(),
			params.getPostalCode()).getId());
		
	}
	
	@GetMapping("/orders/{orderId}")
	public OrderDto findOrder(@RequestAttribute Long userId, @PathVariable Long orderId) 
		throws InstanceNotFoundException, PermissionException {
		
		return toOrderDto(shoppingService.findOrder(userId, orderId));
		
	}
	
	@GetMapping("/orders")
	public BlockDto<OrderSummaryDto> findOrders(@RequestAttribute Long userId, 
		@RequestParam(defaultValue="0") int page) {
		
		Block<Order> orderBlock = shoppingService.findOrders(userId, page, 10);
		
		return new BlockDto<>(toOrderSummaryDtos(orderBlock.getItems()), orderBlock.getExistMoreItems());
		
	}

}
