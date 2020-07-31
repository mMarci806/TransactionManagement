package transaction;

import java.util.*;

public class Main implements IReceiveMessage {

    public static Integer counterForMessages = 0;

    ClientAccount clientAccount1 = new ClientAccount("11111111-22222222", Currency.HUF, (double) 150000);
    ClientAccount clientAccount2 = new ClientAccount("22222222-33333333", Currency.USD, (double) 1230);
    List<ClientAccount> accountList = Arrays.asList(clientAccount1, clientAccount2);
    List<TransactionMessage> transactionMessageList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    @Override
    public void receiveMessage() {
        TransactionMessage transactionMessage = new TransactionMessage();
        System.out.println("Uj tranzakcios uzenet");
        try {
            System.out.println("Szamlaszam :  ");
            String accountNr = scanner.nextLine();
            transactionMessage.setAccountNumber(accountNr);
            System.out.println("Penznem  " + Arrays.asList(Currency.values()) + " : ");
            String curr = scanner.nextLine();
            transactionMessage.setCurrency(Currency.valueOf(curr));
            System.out.println("Osszeg :  ");
            transactionMessage.setAmount(Double.parseDouble(scanner.nextLine()));
            System.out.println("Arfolyam :  ");
            transactionMessage.setExchangeRate(Optional.of( Double.parseDouble(scanner.nextLine())));
        } catch (Exception e) {
            System.err.println("Hibas parameter, kerlek adj uj tranzakcios uzenetet !");
            return;
        }
        manageTransaction(transactionMessage);
    }

    @Override
    public void manageTransaction(TransactionMessage transactionMessage) {
        Optional<ClientAccount> accountForModify = accountList.stream().filter(account -> account.getAccountNumber().equals(transactionMessage.getAccountNumber())).findFirst();
        if (accountForModify.isPresent()) {
            if (accountForModify.get().getCurrency().equals(transactionMessage.getCurrency())) {
                accountForModify.get().setBalance(transactionMessage.getAmount());
            } else {
                try {
                    if (transactionMessage.getExchangeRate().get() == 0)
                        throw new Exception("Hibas arfolyam ertek!");
                    else {
                        accountForModify.get().setBalance(transactionMessage.getAmount() * transactionMessage.getExchangeRate().get());
                        accountList.set(accountList.indexOf(accountForModify.get()), accountForModify.get());
                    }
                } catch (Exception e) {
                    System.err.println("Az arfolyam nem lehet 0 ha a szamla es a tranzakcio valutai nem azonosak egymassal !");
                    return;
                }
            }
            transactionMessageList.add(transactionMessage);
            counterForMessages++;
            if (counterForMessages == 10) {
                System.out.println("Transakcios uzenetek : ");
                for(ClientAccount account : accountList) {
                    System.out.println();
                    System.out.println("Szamlaszam : " + account.getAccountNumber());
                    transactionMessageList.stream().filter(tr -> tr.getAccountNumber().equals(account.getAccountNumber()))
                .forEach(tr -> System.out.println("  " + tr.getAmount() + " " + tr.getCurrency()));
                    System.out.println();
                }
                System.out.println("Szamla egyenlegek : ");
                accountList.stream().forEach(acc -> System.out.println("Szamla : " + acc.getAccountNumber() + " egyenleg =  " + acc.getBalance() +" " + acc.getCurrency()));
                counterForMessages = 0;
                System.out.println();
            }
        } else {
            System.err.println("Nem letezo szamla : " + transactionMessage.getAccountNumber());
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            main.receiveMessage();
        }
    }
}
