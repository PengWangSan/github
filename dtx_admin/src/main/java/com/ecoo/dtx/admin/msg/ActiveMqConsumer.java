package com.ecoo.dtx.admin.msg;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ecoo.dtx.admin.service.DtxTransactionService;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionStatusEnum;

public class ActiveMqConsumer {

	@Autowired
	DtxTransactionService dtxTransactionService;

	@JmsListener(destination = "business")
	public void receiveQueue(byte[] msg) throws IOException {

		DtxTransaction dtxTr = KryoSerializer.deSerialize(msg, DtxTransaction.class);

		if (DtxTransactionStatusEnum.BEGIN.getCode() == dtxTr.getStatus()) {
			dtxTransactionService.begin(dtxTr);
		} else {
			dtxTransactionService.finish(dtxTr);
		}

	}

}
