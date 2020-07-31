package transaction;


public interface IReceiveMessage {

    void receiveMessage();
    void manageTransaction(TransactionMessage transactionMessage);
}
