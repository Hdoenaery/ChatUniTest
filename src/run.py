import os.path
import time

from ChatUniTest.ChatUniTest.src.tools import *
from ChatUniTest.ChatUniTest.src.database import *
from ChatUniTest.ChatUniTest.src.parse_data import parse_data
from ChatUniTest.ChatUniTest.src.export_data import export_data
from ChatUniTest.ChatUniTest.src.scope_test import start_generation
from ChatUniTest.ChatUniTest.src.parse_xml import result_analysis
from ChatUniTest.ChatUniTest.src.task import Task


def clear_dataset():
    """
    Clear the dataset folder.
    :return: None
    """
    # Delete the dataset folder
    if os.path.exists(dataset_dir):
        shutil.rmtree(dataset_dir)


def run():
    """
    Generate the test cases with one-click.
    :return: None
    """
    # Delete history data
    drop_table()

    # Create the table
    create_table()

    # Parse project
    info_path = Task.parse(project_dir)
    print('project_dir = %s' % project_dir)
    print('info_path = %s' % info_path)
    # Parse data
    parse_data(info_path)

    # clear last dataset
    clear_dataset()

    # Export data for multi-process
    export_data()

    project_name = os.path.basename(os.path.normpath(project_dir))

    # Modify SQL query to test the designated classes.
    sql_query = """
        SELECT id FROM method WHERE project_name='{}';
    """.format(project_name)

    # Start the whole process
    start_generation(sql_query, multiprocess=False, repair=True, confirmed=False)

    # Export the result
    result_analysis()


if __name__ == '__main__':
    print("Make sure the config.ini is correctly configured.")
    seconds = 5
    while seconds > 0:
        print(seconds)
        time.sleep(1)  # Pause for 1 second
        seconds -= 1
    run()
