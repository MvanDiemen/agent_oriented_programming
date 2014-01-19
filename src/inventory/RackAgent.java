package inventory;

import behaviours.MessageBehaviour;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

/**
 * This class has the purpose of sending, holding and recieving items Asswell
 * alerting a agent if the stock gets low
 *
 * @author Nico
 *
 */
public class RackAgent extends Agent {

  private String m_name;
  private ArrayList<InventoryItem> m_items;
  private int m_slots;

  @Override
  protected void setup() { //this runs once before starting behaviors
    addBehaviour(new MyBehaviour(this));
    // First set-up answering behaviour
  }

  public class MyBehaviour extends CyclicBehaviour {

    private Agent m_a;
    private ACLMessage m_msg;
    private String[] content_list;

    public MyBehaviour(Agent a) {
      super(a);
      m_a = a;
      m_msg = null;
    }

    @Override
    public void action() {

      // Hier moet de magishe swith of if else bla bla
      addBehaviour(new MessageBehaviour(m_a, null, 10, null, null) {
	public void handleMessage(ACLMessage msg) {
	  m_msg = msg;
	}
      });
      if (m_msg != null) {
	if (m_msg.getPerformative() == ACLMessage.REQUEST) {
	  try {
	    content_list = m_msg.getContent().split("Name: ");
	    content_list = content_list[1].split(", Amount: ");
	  } catch (Exception ex) {

	  }

	  // Check
	}
	if (m_msg.getPerformative() == ACLMessage.PROPOSE) {
	  // Get
	}
      }
      // System.out.println("Agent: " + m_msg.toString());
    }
  }

  public RackAgent() {
    m_name = getLocalName();
    m_items = new ArrayList<>();
  }

  public String getMyName() {
    return m_name;
  }

  public void setName(String name) {
    this.m_name = name;
  }

  public int getSlots() {
    return m_slots;
  }

  public void setSlots(int slots) {
    this.m_slots = slots;
  }

  public ArrayList<InventoryItem> getItems() {
    return m_items;
  }

  public void checkItems(String name, int amount) {

  }

  public void setItems(ArrayList<InventoryItem> items) {
    this.m_items = items;
  }

  public void addItem(InventoryItem item) {
    this.m_items.add(item);
  }

  private void reportItem() {
    //TODO Of we moete hier een behavior in doen, want anders maak ik hier een ACL req in.
  }
}
