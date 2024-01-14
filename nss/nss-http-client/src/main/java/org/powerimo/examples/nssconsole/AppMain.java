package org.powerimo.examples.nssconsole;

import org.powerimo.common.model.SearchQuery;
import org.powerimo.http.okhttp.StdPayloadConverter;
import org.powerimo.nss.api.client.NssHttpClientLocalConfig;
import org.powerimo.nss.httpclient.NssHttpClient;

import java.util.UUID;

public class AppMain {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("ERROR! First argument must be ApiKey");
            return;
        }

        String apiKey = args[0];
        UUID accountId = extractAccountIdString(apiKey);
        System.out.println("Account ID: " + accountId);

        NssHttpClientLocalConfig localConfig = NssHttpClientLocalConfig.builder()
                .apiKey(apiKey)
                .url("http://localhost:15031")
                .accountId(extractAccountIdString(apiKey))
                .sender("example-console-application")
                .build();
        NssHttpClient client = new NssHttpClient(localConfig);
        client.setPayloadConverter(new StdPayloadConverter());

        showAccountList(client);
        showGroupList(client);
        searchGroupByName(client);
    }

    private static void showAccountList(NssHttpClient client) {
        var accountList = client.getAccountList();
        System.out.println("Accounts: ");
        System.out.println(accountList);
        System.out.println();
    }

    private static void showGroupList(NssHttpClient client) {
        SearchQuery query = new SearchQuery();
        query.setLimit(100L);
        var list = client.searchGroups(query);
        System.out.println("Group list:");
        list.forEach(item -> System.out.println("item: " + item.toString()));
        System.out.println();
    }

    private static void searchGroupByName(NssHttpClient client) {
        var group = client.findGroupByName("group2");
        if (group.isPresent()) {
            System.out.println("Group by name found: " + group.get());
        } else {
            System.out.println("Group by name is not found");
        }
        System.out.println();
    }

    public static UUID extractAccountIdString(String s) {
        if (s == null)
            throw new RuntimeException("Parameter must be not null");
        var pos = s.indexOf(":");
        if (pos < 0) {
            throw new RuntimeException("String is not a valid API Key. Must be: <UUID as accountId>:<Password text>");
        }

        String accountPart = s.substring(0, pos);
        return UUID.fromString(accountPart);
    }
}
