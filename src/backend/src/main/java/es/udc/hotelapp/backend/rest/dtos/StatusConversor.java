package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.Status;

public class StatusConversor {

	private StatusConversor() {
	}

	public final static Status toStatus(String statusDto) {
		Status status;
		switch (statusDto) {
		case "LIBRE":
			status = Status.LIBRE;
			break;
		case "OCUPADA":
			status = Status.OCUPADA;
			break;
		case "SIN_LIMPIAR":
			status = Status.SIN_LIMPIAR;
			break;
		default:
			status = null;
			break;
		}

		return status;
	}

	public final static String toStatusDto(Status status) {
		String statusDto;
		switch (status) {
		case LIBRE:
			statusDto = "LIBRE";
			break;
		case OCUPADA:
			statusDto = "OCUPADA";
			break;
		case SIN_LIMPIAR:
			statusDto = "SIN_LIMPIAR";
			break;
		default:
			statusDto = "";
			break;
		}

		return statusDto;
	}

}
