package telran.view;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Menu implements Item{
private String name;
private Item[] items;
private String symbol = "_";
private int nSymbols = 15;

public Menu(Item...items) {
this.items = Arrays.copyOf(items, items.length);
}

public void setSymbol(String symbol) {
    this.symbol = symbol;
}

public void setNsymbols(int nSymbols) {
    this.nSymbols = nSymbols;
}
    @Override
    public String displayName() {
        return name;
    }

    @Override
    public void perform(InputOutput io) {
        displayTitle(io);
        Item item = null;
        boolean running = true;
        do {
            displayItems(io);
            int itemIndex = io.readNumberRange("Select item", "Wrong Item Number", 1, items.length).intValue();
            item = items[itemIndex - 1];
            try {
                item.perform(io);
                running = !item.isExit();
            } catch (RuntimeException e) {
                io.writeLine(e.getMessage());
            }
        }while(running);
    }

    private void displayItems(InputOutput io) {
        IntStream.range(0, items.length).forEach(i -> io.writeLine(String.format("%d. %s", i + 1, items[i].displayName())));
    }

    private void displayTitle(InputOutput io) {
        io.writeString(symbol.repeat(nSymbols));
        io.writeString(name);
        io.writeLine(symbol.repeat(nSymbols));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
